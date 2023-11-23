package ra.academy.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.ICatalogDao;
import ra.academy.model.Catalog;

import java.util.List;
@Service
public class CatalogService implements ICatalogService{
    @Autowired
    private ICatalogDao catalogDao;


    @Override
    public List<Catalog> findAll(int page, int size) {
        // tính limit offset
        // giới hạn lấy về

        return catalogDao.findAll(size,page*size);
    }

    @Override
    public int getTotalPage(int size) {
        int count  = catalogDao.findAll().size();
        if (count%size==0){
            return count/size;
        }
        return count/size+1;
    }

    @Override
    public Catalog findById(Long id) {
        return catalogDao.findById(id);
    }

    @Override
    public void save(Catalog t) {
        catalogDao.save(t);
    }

    @Override
    public void delete(Long id) {
        catalogDao.delete(id);
    }
}
