package com.lambdaschool.microfund;

import com.lambdaschool.microfund.models.*;
import com.lambdaschool.microfund.services.RoleService;
import com.lambdaschool.microfund.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    OrganizationService orgService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process. */ @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        // ROLES //
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("partner");
        Role r3 = new Role("member");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // USERS //

        // admin
        User u1 = new User("llama001@maildrop.cc");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getUseremails()
            .add(new Useremail(u1,
                "kingpin@microfund.test"));

        u1.setFirstname("Wilson");
        u1.setLastname("Fisk");
        u1.setDescription("Businessman and philanthropist.");

        // partner
        User u2 = new User("llama002@maildrop.cc");
        u2.getRoles()
            .add(new UserRoles(u2, r2));
        u2.getUseremails()
            .add(new Useremail(u2,
                "curt@microfund.test"));
        u2.setFirstname("Curtis");
        u2.setLastname("Connors");
        u2.setDescription("Retired Surgeon and Army Veteran.");

        // member
        User u3 = new User("llama003@maildrop.cc");
        u3.getRoles()
            .add(new UserRoles(u3, r3));
        u3.getUseremails()
            .add(new Useremail(u3,
                "ann@microfund.text"));
        u3.setFirstname("Anna");
        u3.setLastname("Watson");
        u3.setDescription("Short but professional Bio.");

        userService.save(u1);
        userService.save(u2);
        userService.save(u3);


        // ORGANIZATIONS

        // main org
        Organization o1 = new Organization("Main Org Title");
        o1.setDescription("Charity organization dedicated to helping real people on the" +
            " ground.");
        o1.getMembers()
            .add(new OrganizationMembers(u1, o1));

        // partner org
        Organization o2 = new Organization("Partner Org Title");
        o2.setDescription("Agricultural Machinery Training");
        o2.getMembers()
            .add(new OrganizationMembers(u2, o2));
        o2.getMembers()
            .add(new OrganizationMembers(u3, o2));

        orgService.save(o1);
        orgService.save(o2);


        // APPLICATIONS

        // main org app






    }
}