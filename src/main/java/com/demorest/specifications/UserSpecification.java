package com.demorest.specifications;

import com.demorest.entities.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UserSpecification {

    private static Specification<User> hasId(UUID id){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id));
    }

    private static Specification<User> hasName(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), String.format("%%%s%%", name.toLowerCase())));
    }

    private static Specification<User> hasEmail(String email){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), String.format("%%%s%%", email.toLowerCase())));
    }

    public Specification<User> buildSpecification(User user){

        Specification<User> spec = Specification.where(null);

        if(user.getId() != null){
            spec = spec.and(UserSpecification.hasId(user.getId()));
        }

        if(user.getName() != null){
            spec = spec.and(UserSpecification.hasName(user.getName()));
        }

        if(user.getEmail() != null){
            spec = spec.and(UserSpecification.hasEmail(user.getEmail()));
        }

        return spec;
    }
}
