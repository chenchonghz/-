package com.orhonit.admin.server.sys.dto;

import java.util.List;

import com.orhonit.admin.server.sys.model.Prescription;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class drugstoreDto {
	private int taskId;
	private int drugstoreId;
	private String name;
	private String username;
	private List<Prescription> prescriptions;
}
