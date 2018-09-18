package com.codecool.guest.book;

import java.util.List;

public interface GuestBookDAO {
    List<Entry> getAllEntries();
    boolean addEntry (Entry entry);
}
