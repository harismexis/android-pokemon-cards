package com.example.jsonfeed.localdb

import androidx.room.*


@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalFeedItem>)

    @Query("SELECT * FROM feed_table WHERE id = :itemId")
    suspend fun getFeedItemById(itemId: Int): LocalFeedItem?

    @Query("SELECT * FROM feed_table")
    suspend fun getFeedItems(): List<LocalFeedItem>?

    @Query("DELETE FROM feed_table")
    suspend fun deleteAll()

    // --------------------------------------------------------

//    // Insert
//
//    /*
//    If the @Insert method receives a single parameter, it can return a long value,
//    which is the new rowId for the inserted item. If the parameter is an array or a collection,
//    then the method should return an array or a collection of long values instead, with each value
//    as the rowId for one of the inserted items
//     */
//
//    @Insert
//    suspend fun insertAll(vararg users: LocalFeedItem)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFeedItem(feedItem: LocalFeedItem)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUsers(vararg users: LocalFeedItem)
//
//    @Insert
//    suspend fun insertBothUsers(user1: LocalFeedItem, user2: LocalFeedItem)
//
//    @Insert
//    suspend fun insertUsersAndFriends(user: LocalFeedItem, friends: List<LocalFeedItem>)
//
//    // Update
//
//    /*
//    Room uses the primary key to match passed entity instances to rows in the database.
//    If there is no row with the same primary key, Room makes no changes.
//    An @Update method can optionally return an int value indicating the number of rows that
//    were updated successfully.
//     */
//
//    @Update
//    suspend fun updateUsers(vararg users: LocalFeedItem)
//
//    // Delete
//
//    @Delete
//    suspend fun deleteUsers(vararg users: LocalFeedItem)
//
//    // Query
//
//    @Query("DELETE FROM feed_table")
//    suspend fun deleteAll()
//
////    @Query("SELECT * from feed_table ORDER BY name DESC LIMIT 1")
////    fun getFeedItems(): LocalFeedItem?
//
//    @Query("SELECT * FROM feed_table")
//    fun loadAllUsers(): Array<LocalFeedItem>
//
//    // Query with filtering params
//
//    @Query("SELECT * FROM feed_table WHERE name > :minAge")
//    fun loadAllUsersOlderThan2(minAge: Int): Array<LocalFeedItem>
//
//    @Query("SELECT * FROM feed_table WHERE name BETWEEN :minAge AND :maxAge")
//    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<LocalFeedItem>
//
//    @Query(
//        "SELECT * FROM user WHERE first_name LIKE :search " +
//                "OR last_name LIKE :search"
//    )
//    fun findUserWithName(search: String): List<LocalFeedItem>
//
//    @Query("SELECT * FROM feed_table WHERE name IN (:regions)")
//    fun loadUsersFromRegions(regions: List<String>): List<LocalFeedItem>

}
