package pl.darczuk.studia.java.enterprise.forest;

import lombok.extern.java.Log;
import pl.darczuk.studia.java.enterprise.entities.Elf;
import pl.darczuk.studia.java.enterprise.entities.Forest;
import pl.darczuk.studia.java.enterprise.entities.User;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

@Stateless
public class ForestService implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext sessionCtx;

    @PermitAll
    public List<Forest> findAllForest() {
        return em.createNamedQuery(Forest.Queries.FIND_ALL, Forest.class).getResultList();
    }

    @PermitAll
    public Forest findForest(int forestId) {
        return em.find(Forest.class, forestId);
    }

    @PermitAll
    public Elf findElf(int elfId) {
        return em.find(Elf.class, elfId);
    }

    @PermitAll
    public void reinforcement(int numberBow) {
        em.createNamedQuery(Elf.Queries.REINFORCEMENT).setParameter("numberBow",numberBow).executeUpdate();
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void saveForest(Forest forest) {
        if (forest.getId() == null) {
            em.persist(forest);
        } else {
            boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
            String login = sessionCtx.getCallerPrincipal().getName();
            boolean isOwner = forest.getOwner().getLogin().equals(login);

            if (isAdmin || isOwner) {
                em.merge(forest);
            } else {
                throw new SecurityException("Insufficient permissions to edit the book");
            }
        }
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void saveElf(Elf elf) {
        if (elf.getId() == null) {
            em.persist(elf);
        } else {
            boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
            String login = sessionCtx.getCallerPrincipal().getName();
            boolean isOwner = elf.getForest().getOwner().getLogin().equals(login);

            if (isAdmin || isOwner) {
                em.merge(elf);
            } else {
                throw new SecurityException("Insufficient permissions to edit the book");
            }
        }
    }

    @RolesAllowed(User.Roles.ADMIN)
    public String removeForest(Forest forest) {
        em.remove(em.merge(forest));
        return "index?faces-redirect=true";
    }

    @RolesAllowed(User.Roles.ADMIN)
    public String removeElf(Elf elf) {
        em.remove(em.merge(elf));
        return "index?faces-redirect=true";
    }

}
