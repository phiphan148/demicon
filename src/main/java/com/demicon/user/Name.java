package com.demicon.user;


import lombok.*;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Name {

    private String title;

    private String first;

    private String last;
}
