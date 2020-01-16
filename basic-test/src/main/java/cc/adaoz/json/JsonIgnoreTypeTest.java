package cc.adaoz.json;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonIgnoreTypeTest {
    public static void main(String args[]) throws IOException {
        Employee emp = new Employee();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
        System.out.println(jsonString);
    }
}

// Employee class
class Employee {
    public Address empAddress = new Address();
    public Map hobby= ImmutableMap.builder()
            .put("pet","dog")
            .put("read","fiction")
            .build();
    ImmutableList.Builder builder;
    public List skills= ImmutableList.builder()
            .add("Math").add("English")
            .build();;
    public long empId = 115;
    public String empName = "Raja Ramesh";

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAddress=" + empAddress +
                '}';
    }

    @JsonIgnoreType
    public static class Address {
        public String firstLine = "null";
        public String secondLine = "null";
        public String thirdLine = "null";

        @Override
        public String toString() {
            return "Address{" +
                    "firstLine='" + firstLine + '\'' +
                    ", secondLine='" + secondLine + '\'' +
                    ", thirdLine='" + thirdLine + '\'' +
                    '}';
        }
    } // end of Address class


}