package ua.kiev.prog.dao;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Group group) {
        entityManager.persist(group);
    }

//    @Override
//    public void delete(Group group) {
//          entityManager.remove(group);
//    }
    @Override
    public void delete(long groupID) {
        Group curGroup = findOne(groupID);
        entityManager.remove(curGroup);
    }
//    @Override
//    public void delete(Group group) {
//        for(Contact c: group.getContacts()) {
//            entityManager.remove(c);
//        }
//        entityManager.remove(group);
//    }

    @Override
    public Group findOne(long id) {
        return entityManager.getReference(Group.class, id);
    }

    @Override
    public List<Group> list() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g", Group.class);
        return query.getResultList();
    }
}
