package com.orhonit.admin.server.sys.dto;

import java.util.List;

import com.orhonit.admin.server.sys.model.Prescriptionm;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class drugstoremDto {
	private int taskId;
	private int drugstoreId;
	private String nameMeng;
	private String username;
	private List<Prescriptionm> prescriptionms;
}
