package org.dao;

import java.util.List;

import org.model.Account;

public interface AccountDAO {
	public boolean createAccount(Account account);
	public List<Account> getAllAccounts();
	public Account getAccount(long accountNo);
	public boolean deleteAccount(long accountNo);
	public boolean updateAccount(long accountNo);
}
