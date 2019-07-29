package com.example.rahulkandoriya.socialappication.fragments;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class ProfileFragment extends PostListFragment {

    public ProfileFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-posts")
                .child(getUid());
    }

}
