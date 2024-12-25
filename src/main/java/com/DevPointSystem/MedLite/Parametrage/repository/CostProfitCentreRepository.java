package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.CostProfitCentre;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CostProfitCentre entity.
 */
@Repository
public interface CostProfitCentreRepository extends JpaRepository<CostProfitCentre, Integer> {

    Collection<CostProfitCentre> findByActif(Boolean actif);   
    
    Collection<CostProfitCentre> findByDetailIn(Collection<Boolean> detail);


    Collection<CostProfitCentre> findByActifIn(Collection<Boolean> actif);
 

    Collection<CostProfitCentre> findByCodeIn(Collection<Integer> codes);

    Collection<CostProfitCentre> findByActifAndCodeSaisieBetween(Boolean actif, String codeDu, String codeAu);
    
//        Collection<CostProfitCentre> findAllByOrderByClassementAsc();
}
