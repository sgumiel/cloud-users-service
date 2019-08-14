package com.cloud.tutorial.usersservice.advancedsearch.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.cloud.tutorial.models.User;
import com.cloud.tutorial.usersservice.advancedsearch.SearchCriteria;
import com.cloud.tutorial.usersservice.advancedsearch.SearchOperation;

public class UserSpecificationsBuilder {

	private final List<SearchCriteria> params;
	
	public UserSpecificationsBuilder() {
		this.params = new ArrayList<>();
	}

	public UserSpecificationsBuilder with(String key, String operation, Object value, String prefix, String suffix) {

		SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
		if (op != null) {
			if (op == SearchOperation.EQUALITY) {
				boolean startWithAsterisk = prefix.contains("*");
				boolean endWithAsterisk = suffix.contains("*");

				if (startWithAsterisk && endWithAsterisk) {
					op = SearchOperation.CONTAINS;
				} else if (startWithAsterisk) {
					op = SearchOperation.ENDS_WITH;
				} else if (endWithAsterisk) {
					op = SearchOperation.STARTS_WITH;
				}
			}
			params.add(new SearchCriteria(key, op, value));
		}
		return this;
	}

	public Specification<User> build() {
		if (params.size() == 0) {
			return null;
		}

		Specification result = new UserSpecification(params.get(0));

		for (int i = 1; i < params.size(); i++) {
			result = Specification.where(result).and(new UserSpecification(params.get(i)));
		}

		return result;
	}
}