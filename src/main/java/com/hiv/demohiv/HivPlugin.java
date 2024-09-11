package com.hiv.demohiv;

import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.IPluginConfigurer;
import org.laxture.sbp.spring.boot.SbpJpaConfigurer;
import org.laxture.sbp.spring.boot.SpringBootstrap;

import org.laxture.sbp.spring.boot.configurer.SbpDataSourceConfigurer;
import org.pf4j.PluginWrapper;

public class HivPlugin extends SpringBootPlugin {


    public HivPlugin(PluginWrapper wrapper) {
        super(wrapper,new SbpJpaConfigurer(new String[] {"com.hiv.demohiv.model"}));}

    @Override
    protected SpringBootstrap createSpringBootstrap() {
        return new SpringBootstrap(this, DemohivApplication.class);
    }
}



