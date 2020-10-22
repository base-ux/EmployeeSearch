package projekti.logic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.domain.Account;

@Service
public class ConnectionsService {

    // Add user alias and name to the connectionRequestsReceived list of visited user account if it's not there yet
    @Transactional
    public void connectionRequestReceived(Account userAccount, Account visitingAccount) {
        for (String[] s : visitingAccount.getConnectionRequestsReceived()) {
            if (s[0].equals(userAccount.getUseralias())) {
                return;
            }
        }
        String[] userInfo = {userAccount.getUseralias(), userAccount.getRealname()};
        visitingAccount.getConnectionRequestsReceived().add(userInfo);
    }

    // Add visited user alias and name to the connectionRequestsSent list of user account if it's not there yet
    @Transactional
    public boolean connectionRequestSent(Account userAccount, Account visitingAccount) {
        for (String[] s : userAccount.getConnectionRequestsSent()) {
            if (s[0].equals(visitingAccount.getUseralias())) {
                return true;
            }
        }
        String[] userInfo = {visitingAccount.getUseralias(), visitingAccount.getRealname()};
        userAccount.getConnectionRequestsSent().add(userInfo);
        return false;
    }
}
