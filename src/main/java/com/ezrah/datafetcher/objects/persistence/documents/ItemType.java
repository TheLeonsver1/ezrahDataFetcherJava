package com.ezrah.datafetcher.objects.persistence.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndexed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A knesset item type, maps to a KNS_Type in knesset api
 */
@Document("kns_item_types")
@Getter
@Setter
@NoArgsConstructor
public class ItemType implements Serializable {
    @Id
    Integer key;

    @PersistentIndexed(unique = true)
    Integer knsTypeID;

    String description;

    /**
     * The datetime the type was added to our db
     */
    @CreatedDate
    LocalDateTime createdDate;

    /**
     * The datetime the type was last updated in our db
     */
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
}
