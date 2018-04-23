package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.SlideDao;
import com.orhonit.admin.server.sys.model.Slide;
import com.orhonit.admin.server.sys.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
	@Autowired
    private SlideDao slideDao;

	@Override
	public void save(Slide slide) {
		// TODO Auto-generated method stub
		slideDao.save(slide);
	}

	@Override
	public Slide getById(Long id) {
		// TODO Auto-generated method stub
		return slideDao.getById(id);
	}

	@Override
	public void update(Slide slide) {
		// TODO Auto-generated method stub
		slideDao.update(slide);
	}

	@Override
	public TableResponse<Slide> list(TableRequest request) {
		// TODO Auto-generated method stub
	 return TableRequestHandler.<Slide> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return slideDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Slide>() {

            @Override
            public List<Slide> list(TableRequest request) {
                return slideDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		slideDao.delete(id);
	}

	@Override
	public List<Slide> getSlideList() {
		// TODO Auto-generated method stub
		return slideDao.getSlideList();
	}
}
