package com.my.micheal.spring.registry;

import com.alibaba.fastjson.JSONObject;
import com.my.micheal.spring.configBean.Protocol;
import com.my.micheal.spring.configBean.Registry;
import com.my.micheal.spring.configBean.Service;
import com.my.micheal.spring.utils.RedissonUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisRegistry implements RegistryCenter{

    @Override
    public boolean registry(Service service, ApplicationContext applicationContext) throws Exception {

        Registry registry = applicationContext.getBean(Registry.class);

        Protocol protocol = applicationContext.getBean(Protocol.class);

        RedissonClient redissonClient = null;
        //创建redis连接
        try {
            redissonClient = RedissonUtil.createRedisConnection(registry.getAddress());
            //redis数据结构为 { 'id':{'address':{'protocol':'','service':''}},{'address':{'protocol':'','service':''}} }
            Map<String,Service> serviceMap = applicationContext.getBeansOfType(Service.class);
            if(serviceMap != null) {
                Service serv = serviceMap.get(service.getId());
                JSONObject  childObject = new JSONObject();
                childObject.put("protocol",registry.getProtocol());
                childObject.put("service",JSONObject.toJSON(serv));
                childObject.put("host",protocol.getHost());
                childObject.put("port",protocol.getPort());
                JSONObject parentObject = new JSONObject();
                parentObject.put(protocol.getHost()+":"+protocol.getPort(),childObject);
                List<String> servicesList =  redissonClient.getList(serv.getId());
                List<String> newServicesList = new ArrayList<>();
                if(servicesList != null && servicesList.size() > 0) {
                    for(String server : servicesList) {
                        JSONObject object = JSONObject.parseObject(server);
                        Set<String> addresses = object.keySet();
                        for(String addr : addresses) {
                            if(addr.equals(protocol.getHost() + protocol.getPort())) {
                                newServicesList.add(parentObject.toJSONString());
                            } else {
                                newServicesList.add(server);
                            }
                        }
                    }

                } else {
                    servicesList.add(parentObject.toJSONString());
                }
                //注册服务
                if(newServicesList != null && newServicesList.size() > 0) {
                    servicesList.clear();
                    servicesList.addAll(newServicesList);
                }
                
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redissonClient.shutdown();
        }
        return false;
    }

    @Override
    public List<String> getAllServices(String id,ApplicationContext applicationContext) {
        Registry registry = applicationContext.getBean(Registry.class);

        RedissonClient redissonClient = null;
        //创建redis连接
        try {
            redissonClient = RedissonUtil.createRedisConnection(registry.getAddress());
            List<String> registryLists = redissonClient.getList(id);
            List<String> registries = new ArrayList<>();
            registries.addAll(registryLists);
            return registries;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redissonClient.shutdown();
        }
        return null;
    }
}
