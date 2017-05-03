package com.taiji.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Sleeb on 2017/5/2.
 */
@Configuration
@ConfigurationProperties(prefix = "hibernate")
public class HibernateConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String dialect;
    private String validation;
    private String showSql;
    private String formatSql;
    private String hbm2ddl;
    private String session;
    private String packages;

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }

    public void setHbm2ddl(String hbm2ddl) {
        this.hbm2ddl = hbm2ddl;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    @Bean(name = "dataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "pooled.c3p0")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() {
        try {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(getDataSource());
            Properties properties = new Properties();
            properties.setProperty("dialect", getDialect());
            properties.setProperty("validation", getValidation());
            properties.setProperty("showSql", getShowSql());
            properties.setProperty("formatSql", getFormatSql());
            properties.setProperty("hbm2ddl", getHbm2ddl());
            properties.setProperty("session", getSession());
            sessionFactory.setHibernateProperties(properties);
            sessionFactory.setPackagesToScan(getPackages());
            sessionFactory.afterPropertiesSet();

            return sessionFactory.getObject();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Bean(name = "hibernateTemplate")
    public HibernateTemplate getHibernateTemplate() {
        HibernateTemplate sessionTemplate = new HibernateTemplate(getSessionFactory());

        return sessionTemplate;
    }

}
