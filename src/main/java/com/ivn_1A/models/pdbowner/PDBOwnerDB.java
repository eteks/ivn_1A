package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.admin.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ets-poc
 */
public class PDBOwnerDB  {
   public List<Pdbversion> GetVersionname() {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
//            String hql = "FROM pdbversion p ORDER BY p.pdb_versionname DESC";
//            Query pdbversion = s.createQuery(hql);
            Query pdbversion = s.createQuery("FROM Pdbversion p order by p.pdb_versionname desc").setParameter("pdb_reference_version", "1.0");
            pdbversion.setMaxResults(1);
//            int result = pdbversion.executeUpdate();
            tx.commit();
            s.clear();
            return pdbversion.list();
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    } 
}
