package com.orhonit.admin.server.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.HerdsmaninfoDao;
import com.orhonit.admin.server.sys.model.Herdsmaninfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/herdsmaninfos")
public class HerdsmaninfoController {

    @Autowired
    private HerdsmaninfoDao herdsmaninfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Herdsmaninfo save(@RequestBody Herdsmaninfo herdsmaninfo) {
        herdsmaninfoDao.save(herdsmaninfo);

        return herdsmaninfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Herdsmaninfo get(@PathVariable Long id) {
        return herdsmaninfoDao.getById(id);
    }
    
    @GetMapping("herdsmaninfo/{id}")
    @ApiOperation(value = "根据uid获取")
    public Herdsmaninfo get(@PathVariable int id) {
        return herdsmaninfoDao.getByUid(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Herdsmaninfo update(@RequestBody Herdsmaninfo herdsmaninfo) {
        herdsmaninfoDao.update(herdsmaninfo);

        return herdsmaninfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Herdsmaninfo> list(TableRequest request) {
        return TableRequestHandler.<Herdsmaninfo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return herdsmaninfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Herdsmaninfo>() {

            @Override
            public List<Herdsmaninfo> list(TableRequest request) {
                return herdsmaninfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        herdsmaninfoDao.delete(id);
    }
}
