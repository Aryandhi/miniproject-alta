package com.alta.miniprojectcheckpoint.service.impl;

import com.alta.miniprojectcheckpoint.model.Users;
import com.alta.miniprojectcheckpoint.repository.UsersRepository;
import com.alta.miniprojectcheckpoint.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.getDistinctTopByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("username not found");
        }
        return users;
    }
}
