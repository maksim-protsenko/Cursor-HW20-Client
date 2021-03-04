package com.cursor.HW20_Client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private long id;
    private String title;
    private boolean available;
    private boolean done;
}