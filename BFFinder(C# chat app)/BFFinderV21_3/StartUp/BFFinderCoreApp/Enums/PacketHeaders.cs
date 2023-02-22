using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Enums
{
    public enum PacketHeaders
    {
        ConectToServerRequest = 1,
        ConnectToServerResponse = 2,
        Dofirst = 3,
        DoSecond = 4,
        LoginRequest = 5,
        LoginResponse = 6,
        RegisterRequest = 7,
        RegisterResponse = 8,
        SendMessageRequest = 9,
        SendMessageResponse = 10,
        FindUserRequest = 11,
        FindUserResponse = 12,
        UpdateUserRequest = 13,
        UpdateUserResponse = 14,  
        LoadGroupRequest = 15,
        LoadGroupResponse = 16,
        LoadMessagesRequest = 17,
        LoadMessagesResponse = 18,
        AllGroupRequest = 19,
        AllGroupResponse = 20,
        AddGroupRequest = 21,
        AddGroupResponse = 22,
        JoinGroupResponse = 23,
        JoinGroupRequest = 24,
        CreateGroupRequest = 25,
        CreateGroupResponse = 26,
        BroadcastMessageResponse = 27,
        CheckUserExistRequest = 28,
        CheckUserExistResponse = 29,
        SendFacultyMesageRequest = 30,
        BroadcastFacultyMessageResponse = 31,
        LoadFacultyRequest = 32,
        LoadFacultyResponse = 33,
    }
}
