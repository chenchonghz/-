package com.orhonit.admin.server.common.datatables;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询返回
 * 
 * @author caodw
 *
 */
@Getter
@Setter
@Builder
public class TableResponse<T> implements Serializable {

	private static final long serialVersionUID = 620421858510718076L;

	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<T> data;

}