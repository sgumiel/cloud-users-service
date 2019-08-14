package com.cloud.tutorial.usersservice.advancedsearch.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.cloud.tutorial.models.User;
import com.cloud.tutorial.usersservice.advancedsearch.SearchCriteria;
import com.cloud.tutorial.usersservice.advancedsearch.SearchOperation;

public class UserSpecification implements Specification<User> {

	private static final long serialVersionUID = 1464970843794095506L;

	private SearchCriteria criteria;

	public UserSpecification(final SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		switch (criteria.getOperation()) {
		case EQUALITY:
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		case NEGATION:
			return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
		case GREATER_THAN:
			return builder.greaterThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		case LESS_THAN:
			return builder.lessThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		case LIKE:
			return builder.like(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		case STARTS_WITH:
			return builder.like(root.<String>get(criteria.getKey()), criteria.getValue() + "%");
		case ENDS_WITH:
			return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue());
		case CONTAINS:
			return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
		default:
			return null;
		}
	}
}
