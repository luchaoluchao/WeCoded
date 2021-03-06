package com.jinva.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class TheDao extends HibernateDaoSupport{

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Serializable save(Object entity){
		return getHibernateTemplate().save(entity);
	}
	
	public void update(Object entity){
		getHibernateTemplate().update(entity);
	}
	
	public void saveOrUpdate(Object entity){
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public List<?> find(Class<?> clazz){
		DetachedCriteria c = DetachedCriteria.forClass(clazz);
		return getHibernateTemplate().findByCriteria(c);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByFileds(Class<T> clazz, String[] fieldNames, Object[] fieldValues) {
		if(fieldNames == null || fieldValues == null || fieldNames.length != fieldValues.length){
			return null;
		}
		DetachedCriteria c = DetachedCriteria.forClass(clazz);
		for(int i = 0; i < fieldNames.length; i ++){
			c.add(Restrictions.eq(fieldNames[i], fieldValues[i]));
		}
		return getHibernateTemplate().findByCriteria(c);
	}
	
	public <T> T findOneByFields(Class<T> clazz, String[] fieldNames, Object[] fieldValues) {
		List<T> result = findByFileds(clazz, fieldNames, fieldValues);
		if (CollectionUtils.isNotEmpty(result)) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public int selectCount(Class<?> clazz, String[] fieldNames, Object[] fieldValues){
		String hql = "select count(*) from " + clazz.getName() + " where ";
		List<String> fieldList = new ArrayList<String>();
		for(String fieldName : fieldNames){
			fieldList.add(fieldName + "=?");
		}
		hql = hql + StringUtils.join(fieldList, " and ");
		List<?> result = getHibernateTemplate().find(hql, fieldValues);
		if (CollectionUtils.isNotEmpty(result)) {
			return Integer.valueOf(result.get(0).toString());
		} else {
			return 0;
		}
	}
	
    public int selectCount(String hql, Object[] params) {
        if (hql.startsWith("from") || hql.startsWith("FROM")) {
            hql = "select count(*) " + hql;
        } else if (hql.startsWith("select") && hql.indexOf("count") == -1 || hql.startsWith("SELECT") && hql.indexOf("COUNT") == -1) {
            hql = "select count(*) from " + hql.substring(hql.indexOf("from") + 4);
        }
        List<?> result = null;
        if (params == null) {
            result = getHibernateTemplate().find(hql);
        } else {
            result = getHibernateTemplate().find(hql, params);
        }
        if (CollectionUtils.isNotEmpty(result)) {
            return Integer.valueOf(result.get(0).toString());
        } else {
            return 0;
        }
    }
	
}
