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
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drugstoreinfos")
public class DrugstoreinfoController {

    @Autowired
    private DrugstoreinfoDao drugstoreinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Drugstoreinfo save(@RequestBody Drugstoreinfo drugstoreinfo) {
        drugstoreinfoDao.save(drugstoreinfo);

        return drugstoreinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Drugstoreinfo get(@PathVariable Long id) {
        return drugstoreinfoDao.getById(id);
    }
    
    @GetMapping("drugstoreinfo/{id}")
    @ApiOperation(value = "根据uid获取")
    public Drugstoreinfo get(@PathVariable int id) {
        return drugstoreinfoDao.getByUid(id);
    }
    
    @GetMapping("status/{id}")
    @ApiOperation(value = "获得所有审核通过的药店")
    public List<Drugstoreinfo> getStatus(@PathVariable int id) {
    	
        return drugstoreinfoDao.getByStatus(id);
    }

    @GetMapping("/passDrugstore/{id}")
    @ApiOperation(value="药店审核通过")
    public int getDrugstoreUid(@PathVariable Long id) {
    	Drugstoreinfo drugstore=drugstoreinfoDao.ById(id);
    	drugstore.setStatus(1);
    	return drugstoreinfoDao.update(drugstore);
	
    }
    
    @GetMapping("/failDrugstore/{id}")
    @ApiOperation(value="药店审核失败")
    public int getDrugstoreFailUid(@PathVariable Long id) {
    	Drugstoreinfo drugstore=drugstoreinfoDao.ById(id);
    	drugstore.setStatus(2);
    	return drugstoreinfoDao.update(drugstore);
	
    }
    
    @PutMapping
    @ApiOperation(value = "修改")
    public Drugstoreinfo update(@RequestBody Drugstoreinfo drugstoreinfo) {
        drugstoreinfoDao.update(drugstoreinfo);

        return drugstoreinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Drugstoreinfo> list(TableRequest request) {
        return TableRequestHandler.<Drugstoreinfo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return drugstoreinfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Drugstoreinfo>() {

            @Override
            public List<Drugstoreinfo> list(TableRequest request) {
                return drugstoreinfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        drugstoreinfoDao.delete(id);
    }
}
