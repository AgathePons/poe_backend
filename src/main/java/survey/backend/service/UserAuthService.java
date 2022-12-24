package survey.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import survey.backend.entities.User;
import survey.backend.entities.UserRole;
import survey.backend.repository.UserRepository;
import survey.backend.dto.UserRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByUserLogin(login).get();
        List<UserRole> userRoles = user.getRoles().stream().collect(Collectors.toList());

        List<GrantedAuthority> grantedAuthorities = userRoles.stream().map(role -> {
           return new SimpleGrantedAuthority(role.getRole());
        }).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(login, user.getUserPassword(), grantedAuthorities);
    }

    public void saveUser (UserRequestDto userRequestDto) {
        if (userRepository.findByUserLogin(userRequestDto.getUserLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUserLogin(userRequestDto.getUserLogin());
        user.setUserPassword(passwordEncoder.encode(userRequestDto.getUserPassword()));

        user.setRoles(userRequestDto.getRoles().stream().map(role -> {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            return userRole;
        }).collect(Collectors.toSet()));

        userRepository.save(user);
    }
}
