package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.StudyarticlemDao;
import com.orhonit.admin.server.sys.model.Studyarticlem;
import com.orhonit.admin.server.sys.service.StudyarticlemService;
@Service
public class StudyarticlemServiceImpl implements StudyarticlemService {
	@Autowired
    private StudyarticlemDao studyarticlemDao;

	@Override
	public void save(Studyarticlem studyarticlem) {
		// TODO Auto-generated method stub
		studyarticlemDao.save(studyarticlem);
	}

	@Override
	public Studyarticlem getById(Long id) {
		// TODO Auto-generated method stub
		return studyarticlemDao.getById(id);
	}

	@Override
	public void update(Studyarticlem studyarticlem) {
		// TODO Auto-generated method stub
		studyarticlemDao.update(studyarticlem);
	}

	@Override
	public TableResponse<Studyarticlem> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Studyarticlem> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return studyarticlemDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Studyarticlem>() {

            @Override
            public List<Studyarticlem> list(TableRequest request) {
                return studyarticlemDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyarticlemDao.delete(id);
	}
	
}
