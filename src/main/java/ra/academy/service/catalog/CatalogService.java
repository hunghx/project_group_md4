package ra.academy.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.ICatalogRepository;
import ra.academy.model.Catalog;

import java.util.List;
@Service
public class CatalogService implements ICatalogService{
    @Autowired
    private ICatalogRepository catalogDao;


    @Override
    public List<Catalog> findAll(int page, int size) {
        // tính limit offset
        // giới hạn lấy về

        return catalogDao.findAll();
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
        return catalogDao.findById(id).orElse(null);
    }

    @Override
    public void save(Catalog t) {
        catalogDao.save(t);
    }

    @Override
    public void delete(Long id) {
        catalogDao.deleteById(id);
    }
}
