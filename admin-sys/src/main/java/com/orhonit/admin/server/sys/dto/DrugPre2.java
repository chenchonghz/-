package com.orhonit.admin.server.sys.dto;

import java.util.List;

import com.orhonit.admin.server.sys.model.Drugstoreinfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugPre2 {
	private Drugstoreinfo drugstoreinfo;
	private List<PrescriptionDto> prescriptionDtos;
}
