package slow.city.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import slow.city.model.User;
import slow.city.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Override
	public User findOneById(Integer id) {
		 
		return null;
	}

	
	
}
