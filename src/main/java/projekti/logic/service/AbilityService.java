package projekti.logic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.domain.Ability;
import projekti.domain.Account;
import projekti.domain.Praise;
import projekti.logic.repository.AbilityRepository;
import projekti.logic.repository.AccountRepository;
import projekti.logic.repository.PraiseRepository;

@Service
public class AbilityService {

    @Autowired
    private AbilityRepository abilityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PraiseRepository praiseRepository;

    // After calling deletePraises method deletes parameter ability from repository if ability was created by parameter user
    @Transactional
    public void deleteAbility(String useralias, Ability ability) {
        if (ability.getAccount().getUseralias().equals(useralias)) {
            List<Praise> praisers = ability.getPraisers();
            deletePraises(praisers);
            this.abilityRepository.delete(ability);
        }
    }

    // Deletes parameter praises from repository
    @Transactional
    public void deletePraises(List<Praise> praisers) {
        for (Praise p : praisers) {
            this.praiseRepository.delete(p);
        }
    }

    // Adds new ability to account of parameter useralias
    public void newAbility(String useralias, String abilitytext) {
        Account account = this.accountRepository.findByUseralias(useralias);
        Ability ability = new Ability();
        ability.setAbilitytext(abilitytext);
        ability.setAccount(account);
        ability.setPraises(0);
        this.abilityRepository.save(ability);
    }

    // Adds new praise to parameter ability and increments the praises integer of parameter ability if it's not there yet
    public void newPraise(Ability ability, String useralias, String praisetext) {
        List<Praise> praisers = ability.getPraisers();
        for (Praise p : praisers) {
            if (p.getUseralias().equals(useralias)) {
                return;
            }
        }
        Praise praise = new Praise();
        praise.setUseralias(useralias);
        praise.setPraisetext(praisetext);
        praise.setAbility(ability);
        this.praiseRepository.save(praise);
        ability.setPraises(ability.getPraises() + 1);
        this.abilityRepository.save(ability);
    }
}
