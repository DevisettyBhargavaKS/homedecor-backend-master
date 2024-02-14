package com.CodeWithBhargav.dataloader;

import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.model.OrderStatus;
import com.CodeWithBhargav.model.Role;
import com.CodeWithBhargav.repository.OrderStatusRepository;
import com.CodeWithBhargav.repository.RoleRepository;
import com.CodeWithBhargav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//        Create user roles
        Role userRole = createRoleIfNotFound(Role.USER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);
        Role vendorRole = createRoleIfNotFound(Role.VENDOR);
//        Create user
        createUserIfNotFound("user", "user", userRole);
        createUserIfNotFound("admin", "admin", adminRole);
        createUserIfNotFound("vendor", "vendor", vendorRole);
        createStatus("Pending");
        createStatus("Confirmed");
        createStatus("Out for Delivery");
        createStatus("Delivered");

        alreadySetup = true;
    }

    private void createStatus(String status) {
//        Optional<OrderStatus> pending = orderStatusRepository.findById(1L);
//        Optional<OrderStatus> confirmed = orderStatusRepository.findById(2L);
//        Optional<OrderStatus> delivery = orderStatusRepository.findById(3L);
//        Optional<OrderStatus> delivered = orderStatusRepository.findById(4L);
//
//        if(pending==null)

        orderStatusRepository.save(new OrderStatus(status));
    }

    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role = roleRepository.findByName(username);
        if (role == null) {
            role = new Role();
            role.setName(username);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private AppUser createUserIfNotFound(final String username, final String password,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = null;
        if (optionalUser.isEmpty()) {
            user = new AppUser();
            user.setUsername(username);
            user.setName(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(role);
            user = userRepository.save(user);
        }
        return user;
    }
}
