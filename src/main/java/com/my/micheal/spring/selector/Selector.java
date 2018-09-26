package com.my.micheal.spring.selector;

import java.util.List;

public interface Selector {
    NodeInfo selectNode(List<String> services) throws Exception;
}
