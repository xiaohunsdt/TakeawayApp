package net.novaborn.takeaway.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseKVO<k,V> implements Serializable {
    k key;
    V value;
}
