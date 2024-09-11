//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.yourpackage"})
//@EnableJpaRepositories(basePackages = {"com.yourpackage.repository"})
//public class appConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan(new String[] { "com.yourpackage.model" });
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return em;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }
//}
