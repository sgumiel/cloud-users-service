package com.cloud.tutorial.usersservice.advancedsearch;

import java.io.Serializable;

import com.cloud.tutorial.usersservice.advancedsearch.SearchOperation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria implements Serializable{

	private static final long serialVersionUID = 1381908504051596818L;
	
	private String key;
    private SearchOperation operation;
    private Object value;
}