package ra.academy.service.catalog;

import ra.academy.model.Catalog;

import java.util.List;

public interface ICatalogService {
    List<Catalog> findAll(int page, int size);
    Catalog findById(Long id);
    void save(Catalog t);
    void delete(Long id);
    int getTotalPage(int size);
}
