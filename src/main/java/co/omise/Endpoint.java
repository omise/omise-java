package co.omise;

import com.google.common.collect.ImmutableList;
import okhttp3.HttpUrl;

public abstract class Endpoint {
    public static final Endpoint VAULT = new Endpoint() {
        @Override
        public String host() {
            return "vault.omise.co";
        }

        @Override
        public String authenticationKey(Config config) {
            return config.publicKey();
        }
    };

    public static final Endpoint API = new Endpoint() {
        @Override
        public String host() {
            return "api.omise.co";
        }

        @Override
        public String authenticationKey(Config config) {
            return config.secretKey();
        }
    };

    public static ImmutableList<Endpoint> all() {
        return ImmutableList.of(
                Endpoint.API,
                Endpoint.VAULT
        );
    }

    public String scheme() {
        return "https";
    }

    public abstract String host();

    public String certificateHash() {
        return "sha256/maqNsxEnwszR+xCmoGUiV636PvSM5zvBIBuupBn9AB8=";
    }

    public abstract String authenticationKey(Config config);

    public HttpUrl.Builder buildUrl() {
        return new HttpUrl.Builder()
                .scheme(scheme())
                .host(host());
    }
}

