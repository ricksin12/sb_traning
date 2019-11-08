package com.first;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@SuppressWarnings({ "deprecation", "unchecked" })
@Service
@Transactional
public class FirstCrudSvc {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager em;

	public ComPo ins(FirstCrudVo firstCrudVo, HttpServletRequest request) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO hr_salary.SB_TRAINING (			 ");
		sql.append("     name                          ");
		if (!"none".equals(firstCrudVo.getSex())) {
			sql.append("     ,sex                          ");
		}
		sql.append(" ) VALUES (                              ");
		sql.append("     :name                		     ");
		if (!"none".equals(firstCrudVo.getSex())) {
			sql.append("     ,:sex                          ");
		}
		sql.append(" )                                       ");
		logger.info("sql=" + sql);
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("name", firstCrudVo.getName());
		if (!"none".equals(firstCrudVo.getSex())) {
			query.setParameter("sex", firstCrudVo.getSex());
		}
		if (query.executeUpdate() == 1) {
			return new ComPo("1");
		} else {
			return new ComPo("-1");
		}
	}

	public ComPo sel(FirstCrudVo firstCrudVo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT												");
		sql.append(" a.id,                                          ");
		sql.append(" a.name                                          ");
		sql.append(" FROM                                           ");
		sql.append("     SB_TRAINING a                                     ");
		sql.append(" WHERE                                          ");
		sql.append("     1 = 1                                      ");
		if (StringUtils.isNotBlank(firstCrudVo.getName())) {
			sql.append("     AND REGEXP_LIKE ( a.name,               ");
			sql.append("                       :name,                ");
			sql.append("                       'i' )                    ");
		}
		if (StringUtils.isNotBlank(firstCrudVo.getId())) {
			sql.append("     AND a.id = :id               ");
		}
		if (!"none".equals(firstCrudVo.getSex()) && StringUtils.isNotBlank(firstCrudVo.getSex())) {
			sql.append("     AND  a.sex IN (:sex)              ");
		}
		logger.info("sql=" + sql);
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (StringUtils.isNotBlank(firstCrudVo.getName())) {
			query.setParameter("name", firstCrudVo.getName());
		}
		if (StringUtils.isNotBlank(firstCrudVo.getId())) {
			query.setParameter("id", firstCrudVo.getId());
		}
		if (!"none".equals(firstCrudVo.getSex()) && StringUtils.isNotBlank(firstCrudVo.getSex())) {
			query.setParameter("sex", Arrays.asList(firstCrudVo.getSex().split(",")));
		}
		List<Map<String, Object>> rowList = (List<Map<String, Object>>) query.getResultList();
		return new ComPo(rowList);
	}

	public ComPo upd(FirstCrudVo firstCrudVo, HttpServletRequest request) {
		firstCrudVo.setId(firstCrudVo.getId());
		int rowNum = updCom(firstCrudVo, request, "upd");
		if (rowNum == 1) {
			return new ComPo("2");
		}
		if (rowNum == 0) {
			return new ComPo("-2.1");
		} else {
			return new ComPo("-2");
		}
	}

	private int updCom(FirstCrudVo firstCrudVo, HttpServletRequest request, String method) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE SB_TRAINING						                                                      ");
		sql.append(" SET                                                                                      ");
		if (StringUtils.isNotBlank(firstCrudVo.getName())) {
			sql.append("     name = :name                                                                        ");
		}
		if (!"none".equals(firstCrudVo.getSex())) {
			sql.append("     ,sex= :sex                                                                        ");
		} else {
			sql.append("     ,sex= null                                                                        ");
		}
		sql.append(" WHERE                                                                                    ");
		sql.append("     id in (:id)                                                                         ");

		Query query = em.createNativeQuery(sql.toString());
		if (StringUtils.isNotBlank(firstCrudVo.getName())) {
			query.setParameter("name", firstCrudVo.getName());
		}
		if (!"none".equals(firstCrudVo.getSex())) {
			query.setParameter("sex", firstCrudVo.getSex());
		}
		query.setParameter("id", firstCrudVo.getId());

		return query.executeUpdate();
	}

	public ComPo del(FirstCrudVo firstCrudVo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from SB_TRAINING ");
		sql.append(" where ID in (:id) ");
		Query query = em.createNativeQuery(sql.toString());
		List<String> idListString = Arrays.asList(firstCrudVo.getIdList().split(","));
		query.setParameter("id", idListString);
		int rowNum = query.executeUpdate();
		logger.info("rowNum=" + rowNum);
		logger.info("idListString=" + idListString);
		logger.info("idListString.size()=" + idListString.size());
		if (rowNum == idListString.size()) {
			ComPo comOutVo = sel(firstCrudVo);
			comOutVo.setStatus("3");
			return comOutVo;
		} else if (rowNum < idListString.size()) {
			return new ComPo("-3.1");
		} else {
			return new ComPo("-3");
		}
	}

}