package com.nmq.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 通过google protobuf对message object实行序列化和反序列化
 * @author niemengquan
 * @create 2019/6/6
 * @modifyUser
 * @modifyDate
 */
public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder().setId(1234).setName("张三").setAddress("北京市朝阳区").build();

        byte[] bytes = person.toByteArray();
        AddressBookProtos.Person dePerson = AddressBookProtos.Person.parseFrom(bytes);
        System.out.println(dePerson);
        System.out.println(dePerson.getId());
        System.out.println(dePerson.getName());
        System.out.println(dePerson.getAddress());

    }
}
