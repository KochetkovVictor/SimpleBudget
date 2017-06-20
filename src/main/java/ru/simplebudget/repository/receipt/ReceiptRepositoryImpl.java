package ru.simplebudget.repository.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.common.Shop_;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.out.Receipt_;
import ru.simplebudget.model.user.User;
import ru.simplebudget.repository.purse.PurseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional(readOnly = true)
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @PersistenceContext
    private EntityManager em;

    private final PurseRepository purseRepository;

    @Autowired
    public ReceiptRepositoryImpl(PurseRepository purseRepository) {
        this.purseRepository = purseRepository;
    }

    @Override
    @Transactional
    public Receipt save(Receipt receipt, Long userId) {
        if (receipt.getId() != null && get(receipt.getId(), userId) == null) {
            return null;
        }
        receipt.setUser(em.getReference(User.class, userId));
        if (receipt.getId() == null) {
            em.persist(receipt);
            //purseRepository.addPurseAmount(receipt.getPurse().getId(), userId, -receipt.getAmount());
            return receipt;
        } else {
            return em.merge(receipt);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id, Long userId) {
        Receipt receipt = get(id, userId);
        if (receipt != null && receipt.isActive()) {
            receipt.setActive(false);
            //purseRepository.addPurseAmount(receipt.getPurse().getId(), userId, receipt.getAmount());
            //save(receipt, userId);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Receipt> cd = cb.createCriteriaDelete(Receipt.class);
            Root<Receipt> root = cd.from(Receipt.class);
            Path<User> user = root.get(Receipt_.user);
            cd.where(cb.and(cb.equal(user.get("id"), userId), cb.equal(root.get("id"), id)));
            em.createQuery(cd).executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public Receipt get(Long id, Long userId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> criteriaQuery = cb.createQuery(Receipt.class);
        Root<Receipt> root = criteriaQuery.from(Receipt.class);
        Path<User> user=root.get(Receipt_.user);
        Predicate condition = cb.and(cb.equal(user.get("id"), userId),cb.equal(root.get(Receipt_.id), id));
        criteriaQuery.where(condition);
        return  em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Receipt> getByPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Receipt.GET_BETWEEN_DATETIME, Receipt.class).setParameter("userId", userId)
                .setParameter("startDateTime", startDate).setParameter("endDateTime", endDate).getResultList();
    }

    @Override
    public List<Receipt> getAllByShop(Long userId, Shop shop) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq=cb.createQuery(Receipt.class);
        Root<Receipt> root=cq.from(Receipt.class);
        Path<Shop> shopPath=root.get(Receipt_.shop);
        Path<User> user=root.get(Receipt_.user);
        Predicate condition=cb.and(cb.equal(user.get("id"), userId), cb.equal(shopPath.get("id"), shop.getId()));
        cq.where(condition);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Receipt> getAllByShopNet(Long userId, Long shopNetId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Receipt.JOIN_SHOPNET_GET_BETWEEN_DATETIME, Receipt.class)
                .setParameter("userId", userId)
                .setParameter("shopNetId", shopNetId)
                .setParameter("startDateTime", startDate)
                .setParameter("endDateTime", endDate)
                .getResultList();
    }

    @Override
    public List<Receipt> getAll(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        Path<LocalDate> date = root.get(Receipt_.receiptDate);
        Path<User> user =root.get(Receipt_.user);
        Predicate condition=cb.equal(user.get("id"), userId);
        cq.where(condition);
        cq.orderBy(cb.asc(date));
        TypedQuery<Receipt> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Receipt changeReceipt(Receipt changeReceipt, Long userId) {
        Receipt receipt = get(changeReceipt.getId(), userId);
        Double oldAmount = receipt.getAmount();
        Purse oldPurse = receipt.getPurse();
        if (!receipt.getShop().equals(changeReceipt.getShop())) {
            receipt.setShop(changeReceipt.getShop());
        }
        if (!Objects.equals(oldAmount, changeReceipt.getAmount())) {
            receipt.setAmount(changeReceipt.getAmount());
            if (Objects.equals(oldPurse.getId(), changeReceipt.getPurse().getId())) {
                //purseRepository.addPurseAmount(changeReceipt.getPurse().getId(), userId, -changeReceipt.getAmount() - oldAmount);
            } else {
               // purseRepository.addPurseAmount(oldPurse.getId(), userId, oldAmount);
              //  purseRepository.addPurseAmount(changeReceipt.getPurse().getId(), userId, -changeReceipt.getAmount());
                receipt.setPurse(changeReceipt.getPurse());
            }
        } else if (!Objects.equals(oldPurse.getId(), changeReceipt.getPurse().getId())) {
          //  purseRepository.addPurseAmount(oldPurse.getId(), userId, oldAmount);
           // purseRepository.addPurseAmount(changeReceipt.getPurse().getId(), userId, -changeReceipt.getAmount());
            receipt.setPurse(changeReceipt.getPurse());
        }
        return em.merge(receipt);
    }
}
