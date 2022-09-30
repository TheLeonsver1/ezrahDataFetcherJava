package com.ezrah.datafetcher.objects.persistence.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndexed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Document
@Getter
@Setter
@NoArgsConstructor
public class ItemType implements Serializable {
    @Id
    Integer key;

    @PersistentIndexed(unique = true)
    Integer knsTypeID;

    String description;

}
