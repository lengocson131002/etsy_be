package com.app.commerce.repository.projections;

public interface ProfileStatusProjection {
    Integer getLogoutCount();

    Integer getFailedProxyCount();

    Integer getDeletedCount();

    Integer getTooManyRequestCount();

    Integer getEmptyCount();

    Integer getSyncCount();
}
