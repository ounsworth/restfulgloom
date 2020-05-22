package ca.flearning.restfulgloom.entities;

import org.springframework.security.crypto.codec.Hex;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * For persisting auth refresh tokens to the db.
 */
@Entity
@Table(name="REFRESHTOKENS")
public class RefreshToken {

    //TODO: discuss with Mark. I'm probably being sloppy here because I'm mixing model
    //TODO: and controller in one file. Fix?

    /* BEAN */

    //TODO: I'm not sure if I need the @Size constraints, maybe Hibernate is smart enough to
    //TODO: figure that out from the DB schema?

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long tokenId;

    private String token;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;

    private String username;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return token;
    }


    public void setRefreshToken(String refreshToken) { this.token = refreshToken; }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date issuedAt) {
        this.expiry = issuedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    /* LOGIC */

    private static final int REFRESH_TOKEN_LEN = 16;
    private static final long EXPIRATION_TIME = 86_400_000;  // 24 hours

    public static RefreshToken generateToken() {
        RefreshToken refreshToken = new RefreshToken();
        try {
            // generate a 32 byte random
            byte[] refreshTokenBytes = new byte[REFRESH_TOKEN_LEN];
            SecureRandom.getInstanceStrong().nextBytes(refreshTokenBytes);
            refreshToken.setToken(new String(Hex.encode(refreshTokenBytes)));
            refreshToken.setExpiry(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        } catch (NoSuchAlgorithmException e) {
            // something went wrong, don't try to return anything
            e.printStackTrace();
        }
        return refreshToken;
    }
}
