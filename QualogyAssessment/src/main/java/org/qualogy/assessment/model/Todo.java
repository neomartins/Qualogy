package org.qualogy.assessment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Todo {

     @Id
     private String id;
     private String description;
     private boolean status;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public boolean isStatus() {
          return status;
     }

     public void setStatus(boolean status) {
          this.status = status;
     }

     @Override
     public String toString() {
          return "Todo [description=" + description + ", status=" + status + "]";
     }

}
