package com.example.besammenprojekt.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.besammenprojekt.R;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private RecyclerView users_RecyclerView;
    private List<Users> mUsers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        users_RecyclerView = (RecyclerView) findViewById(R.id.users_recyclerview);
        users_RecyclerView.setLayoutManager((new LinearLayoutManager(this)));
        mUsers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Users user = new Users();
            user.setFirstName("User #" + i);
            user.setEmail("user" + "alisa@love.dk");
            mUsers.add(user);
        }

        users_RecyclerView.setAdapter((new UsersAdapter(mUsers)));
    }

    class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
        private List<Users> mUsers;

        public UsersAdapter(List<Users> users) {
            super();
            this.mUsers = users;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.bind(this.mUsers.get(position));
        }

        @Override
        public int getItemCount() {
            return this.mUsers.size();
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView firstName;
        private TextView email;

        public UserViewHolder(ViewGroup container) {
            super(LayoutInflater.from(UserActivity.this).inflate(R.layout.user_list_item, container, false));
            firstName = (TextView) itemView.findViewById(R.id.firstname_text);
            email = (TextView) itemView.findViewById(R.id.email_text);
        }

        public void bind(Users user) {
            firstName.setText(user.getFirstName());
            email.setText(user.getEmail());
        }
    }

    class Users {
        private String firstName;
        private String email;

        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

    }
}
