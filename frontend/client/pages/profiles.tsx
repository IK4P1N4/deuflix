import axios from "axios";
import { NextPage, GetServerSideProps } from "next";
import Head from "next/head";
import Image from "next/image";
import React from "react";
import { useRouter } from "next/router";

type Profile = {
    id: number;
    UId: number;
    pName: string;
};

interface ProfilesPageProps {
    profiles: Profile[];
}

const ProfilesPage: NextPage<ProfilesPageProps> = ({ profiles }) => {
    const router = useRouter();

    const handleProfileClick = (profileId: number) => {
        console.log(`Profile ${profileId} clicked`);
        router.push("/");
    };

    return (
        <div className="relative flex h-screen w-screen flex-col md:items-center md:justify-center md:bg-transparent">
            <Head>
                <title>NetFlix-Profiles</title>
            </Head>
            <Image
                src="https://assets.nflxext.com/ffe/siteui/vlv3/79fe83d4-7ef6-4181-9439-46db72599559/bd4f2024-8853-47ee-b84b-779b52fd5f12/TR-tr-20221017-popsignuptwoweeks-perspective_alpha_website_small.jpg"
                className="-z-10 opacity-30 sm:!inline h-screen w-screen"
                layout="fill"
                objectFit="cover"
            />
            <div>
                <img
                    src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Netflix_2015_logo.svg/2560px-Netflix_2015_logo.svg.png"
                    className="absolute left-4 top-4 cursor-pointer object-contain md:left-10 md:top-6"
                    width={150}
                    height={150}
                />
            </div>

            <div className="flex flex-col items-center justify-center h-screen z-10">
                <h1 className="text-4xl font-semibold mb-8">Deuflix를 실행할 프로필을 선택해주세요.</h1>
                {/* 프로필 선택 UI */}
                <div className="flex space-x-4">
                    {profiles.map((profile) => (
                        <button
                            key={profile.id}
                            onClick={() => handleProfileClick(profile.id)}
                            className="bg-gAray-800 text-white px-4 py-2 rounded cursor-pointer z-10"
                        >
                            {profile.pname || 'No Name'}
                        </button>
                    ))}
                </div>
            </div>
        </div>
    );
};

export const getServerSideProps: GetServerSideProps = async ({ query }) => {
    try {
        const userId = query.userId;
        // 사용자 ID를 이용해서 프로필 데이터를 가져옴
        const { data } = await axios.get(`${process.env.NEXT_PUBLIC_API}/profiles/${userId}`);
        console.log("Profiles data:", data); // API 응답 데이터 확인
        return {
            props: {
                profiles: data,
            },
        };
    } catch (error) {
        console.error("Failed to fetch profiles:", error);
        return {
            props: {
                profiles: [],
            },
        };
    }
};

export default ProfilesPage;
