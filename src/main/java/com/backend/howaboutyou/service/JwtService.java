package com.backend.howaboutyou.service;

import com.backend.howaboutyou.config.JwtProvider;
import com.backend.howaboutyou.domain.RefreshToken;
import com.backend.howaboutyou.domain.Token;
import com.backend.howaboutyou.exception.RefreshTokenNotExistsException;
import com.backend.howaboutyou.exception.entity.ErrorCode;
import com.backend.howaboutyou.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /*
    * 로그인은 반드시 리프레시 토큰을 디비에 새로 저장한다.
    * 이미 리프레시 토큰이 있다면 삭제하고 새로 저장한다.
    * */
    @Transactional
    public void login(Token token){
        RefreshToken refreshToken = RefreshToken.builder()
                .keyEmail(token.getKey())
                .refreshTokenString(token.getRefreshToken())
                .build();
        String loginUserEmail = refreshToken.getKeyEmail();
        if(refreshTokenRepository.existsByKeyEmail(loginUserEmail)){
            refreshTokenRepository.deleteByKeyEmail(loginUserEmail); // 기존 토큰 삭제
        }
        refreshTokenRepository.save(refreshToken);
    }

    /*
    * jwtProvider.validateRefreshToken을 통해 만들어진 createdAccessToken은
    * refreshToken의 만료기한이 정상이어야 생성되고 만료기한이 지나거나 토큰에 문제가 있다면 null이다.
    * createdAccessToken이 null이면 402상태를 전송, 아니라면 토큰을 전송한다.
    * */
    public String getNewAccessToken(String keyEmail){
        RefreshToken refreshToken = refreshTokenRepository.findByKeyEmail(keyEmail)
                .orElseThrow(() -> new RefreshTokenNotExistsException(ErrorCode.REFRESHTOKEN_NOT_EXISTS));
        // refresh 토큰이 검증을 통과하면 새로운 access Token을 발급한다.
        return jwtProvider.validateRefreshToken(refreshToken);
    }

}