package com.kingleadsw.ysm.swagger;

import springfox.documentation.service.Contact;

public class Author
        extends Contact
{
    public Author(String name)
    {
        super(name, "", "");
    }

    public Author(String name, String url, String email)
    {
        super(name, email, url);
    }
}