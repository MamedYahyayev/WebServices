package az.maqa.project.dao.inter;

import az.maqa.project.model.Informations;

import java.util.List;

public interface InformationsDao {
    List<Informations> getInformationsList() throws Exception;

    boolean add(Informations informations) throws Exception;

    Informations getInformationById(Long id) throws Exception;

    boolean update(Informations informations, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Informations> search(String keyword) throws Exception;
}
